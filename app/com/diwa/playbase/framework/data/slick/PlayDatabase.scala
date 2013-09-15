package scala.slick.session

abstract class PlayDatabase extends Database {
  protected def datasource: javax.sql.DataSource
  protected[session] def createConnection(): java.sql.Connection = datasource.getConnection
}
