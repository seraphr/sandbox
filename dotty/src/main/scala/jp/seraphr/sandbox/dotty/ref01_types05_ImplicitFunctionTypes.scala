package jp.seraphr.sandbox.dotty

object ImplicitFunctionTypes {
  type ImplicitFunc = implicit String => Int
  def m(implicit s: String): Int = ???

  def method(f: ImplicitFunc): Int = f("hoge")
}

object RunTableDSL {
  def main(args: Array[String]): Unit = {
    import TableDSL._

    val tTable = table {
      row {
        cell("1-1")
        cell("1-2")
        cell("1-3")
      }

      // cell("aa") // rowの外でcellを呼ぶとコンパイルエラー

      row {
        cell("2-1")
        cell("2-2")
        cell("2-3")
      }
    }

    println(tTable)
    // Table(
    //   Row(1-1       1-2     1-3)
    //   Row(2-1       2-2     2-3)
    // )
  }
}

object TableDSL {
  import scala.collection.mutable.ArrayBuffer
  type TableF = implicit Table => Table
  type RowF = implicit Row => Row

  def table(f: TableF): Table = {
    f(new Table)
  }

  def row(f: RowF)(implicit t: Table): Table = {
    t.addRow(f(new Row))
    t
  }

  def cell(s: String)(implicit r: Row): Row = {
    r.addCell(Cell(s))
    r
  }

  class Table {
    private val mRows = new ArrayBuffer[Row]

    def addRow(aRow: Row): this.type = {
      mRows += aRow
      this
    }

    override def toString(): String = {
      def rows = mRows.mkString("\n")

      s"Table(\n${rows}\n)"
    }
  }
  class Row {
    private val mCells = new ArrayBuffer[Cell]
    def addCell(aCel: Cell): this.type = {
      mCells += aCel
      this
    }

    override def toString(): String = {
      s"  Row(${mCells.map(_.v).mkString("\t")})"
    }
  }

  case class Cell(v: String)
}