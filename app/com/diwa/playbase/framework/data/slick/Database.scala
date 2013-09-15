package com.diwa.playbase.framework.data.slick

import com.google.inject._
import scala.slick.session.{ ResultSetConcurrency, Session }
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException
import java.sql.SQLException
import com.hirepro.framework.data.slick._
import scala.Some

// this allows us to replace the database session implementation in tests
// and check when sessions are being obtained
@ImplementedBy(classOf[SlickSessionProviderImpl])
trait SlickSessionProvider {
  def createReadOnlySession(handle: SlickDatabase): Session
  def createReadWriteSession(handle: SlickDatabase): Session
}

@Singleton
class SlickSessionProviderImpl extends SlickSessionProvider {
  def createReadOnlySession(handle: SlickDatabase): Session = {
    handle.createSession().forParameters(rsConcurrency = ResultSetConcurrency.ReadOnly)
  }
  def createReadWriteSession(handle: SlickDatabase): Session = {
    handle.createSession().forParameters(rsConcurrency = ResultSetConcurrency.Updatable)
  }
}

class Database @Inject() (val db: DataBaseComponent, val sessionProvider: SlickSessionProvider) /*extends Logging*/ {

  import DBSession._

  val dialect: DatabaseDialect[_] = db.dialect

  // def readOnlyAsync[T](f: ROSession => T): Future[T] = future { readOnly(f) }
  // def readWriteAsync[T](f: RWSession => T): Future[T] = future { readWrite(f) }
  // def readWriteAsync[T](attempts: Int)(f: RWSession => T): Future[T] = future { readWrite(attempts)(f) }

  def readOnly[T](f: ROSession => T): T = {
    var s: Option[Session] = None
    val ro = new ROSession({
      s = Some(sessionProvider.createReadOnlySession(db.handle))
      s.get
    })
    try f(ro) finally s.foreach(_.close())
  }

  def readWrite[T](f: RWSession => T): T = {
    val s = sessionProvider.createReadWriteSession(db.handle)
    try {
      s.withTransaction {
        f(new RWSession(s))
      }
    } finally s.close()
  }

  def readWrite[T](attempts: Int)(f: RWSession => T): T = {
    1 to attempts - 1 foreach { attempt =>
      try {
        return readWrite(f)
      } catch {
        case ex: MySQLIntegrityConstraintViolationException =>
          throw ex
        case t: SQLException =>
          val throwableName = t.getClass.getSimpleName
        //   log.warn(s"Failed ($throwableName) readWrite transaction attempt $attempt of $attempts")
      }
    }
    readWrite(f)
  }
}
