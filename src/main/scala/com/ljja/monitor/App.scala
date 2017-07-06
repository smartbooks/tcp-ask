package com.ljja.monitor

import scala.io.StdIn
import scala.async.Async._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

object App {

  val timeout = 1000
  val portStart = 0
  val portEnd = 65535
  var host = "www.baidu.com"

  def main(args: Array[String]): Unit = {

    if (args != null && args.length > 0) {
      host = args(0).trim()
    }

    val future = async {

      for (i <- portStart to portEnd) {
        val t = new Thread(new Runnable {
          override def run() = {
            val tcp = new TcpSocketPortMonitor(host, i, timeout).open()
            //println(s"${host},${i},${tcp}")
            if (tcp) {
              println(s"${host},${i},${tcp}")
            }
          }
        })
        t.start()
        Thread.sleep(1)
      }
    }

    while (future.isCompleted == false) {

    }

    println("exit")

  }

}
