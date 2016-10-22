object main {
  def printTree(n: Node, depth: Int = 0, childrenBefore: Int = 0, childrenAfter: Int = 0): Unit = {
    println(n.string(depth, childrenBefore, childrenAfter))
    var traversed_children = 0
    n match {
      case branch: Branch =>
        val children = branch.children()
        children.foreach((n: Node) => {
          printTree(n, depth + 1, traversed_children, children.length - traversed_children - 1)
          traversed_children += 1
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