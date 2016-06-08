object main {
  def printTree(n: Node, depth: Integer = 0, childrenBefore: Integer = 0): Unit = {
    println(n.string(depth, childrenBefore))
    var children = 0
    n match {
      case branch: Branch =>
        branch.children().foreach((n: Node) => {
          printTree(n, depth + 1, children)
          children += 1
        })
      case _ =>
    }
  }

  def main(args: Array[String]) {
    val b21 = Branch(Leaf("asd"), Leaf("123"))

    val b22 = Branch(Leaf("Satoshi Nakamoto"), Leaf("Hal Finney"))

    val b11 = Branch(b21, b22)

    printTree(b11)
  }
}