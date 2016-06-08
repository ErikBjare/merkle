import com.roundeights.hasher.Implicits._

class MerkleTree(var root: Node)

trait Node {
  def hash(): String

  def string(depth: Integer = 0, childrenBefore: Integer = 0): String = {
    val nodeType = this match {
      case x: Branch => "branch"
      case x: Leaf => "leaf"
    }

    // │ ├ ─ └ ┌
    val treeRoot = s"${if(depth == 0) "┌" else ""}${"│"*(depth-1)}${if (depth > 0) (if(nodeType.equals("branch")) "├┬" else (if (childrenBefore == 1) "└" else "├")) else ""}"
    val treeArm = s"${"─"*(depth*1+1)}"
    val label = s"${nodeType}\t${hash}"
    treeRoot + treeArm + label
  }
}

class Branch(private val childs: List[Node]) extends Node {
  def children(): List[Node] = {
    childs
  }

  def addChild(child: Node): Unit = {
    childs :+ child
  }

  override def hash(): String = {
    MerkleTree.computeHash(childs.map((n: Node) => n.hash()).toArray.mkString)
  }
}

object Branch {
  def apply(children: Node*): Branch = {
    new Branch(children.toList)
  }
}

class Leaf(data: String) extends Node {
  def get(): String = data

  override def hash(): String = {
    MerkleTree.computeHash(get())
  }
}

object Leaf {
  def apply(data: String): Leaf = {
    new Leaf(data)
  }
}

object MerkleTree {
  def computeHash(data: String): String = {
    data.sha256.hex
  }
}