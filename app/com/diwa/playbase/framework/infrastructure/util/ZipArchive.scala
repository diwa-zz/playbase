package com.diwa.playbase.framework.infrastructure.util

import java.util.zip.ZipFile
import java.io._
import scala.collection.JavaConversions._
import Loans.using
import scala.annotation.tailrec

class ZipArchive(val file: File) {
  val BUFSIZE = 4096
  val buffer = new Array[Byte](BUFSIZE)

  def unZip: Map[String, String] = {
    using(new ZipFile(file)) { zipFile =>
      (
        for {
          entry <- zipFile.entries.toList if !entry.isDirectory
          content = using(zipFile.getInputStream(entry)) { readStream(_, "") }
        } yield (entry.getName, content)
      ).toMap
    }
  }

  @tailrec
  private def readStream(fis: InputStream, content: String): String = {
    val length = fis.read(buffer)
    val data = new String(buffer.take(length))
    if (length >= 0)
      readStream(fis, content + data)
    else
      content
  }
}
