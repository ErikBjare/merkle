import com.roundeights.hasher.Implicits._
import collection.mutable

import MerkleDAG.Edge

class MerkleDAG {
  private var _nodes: Set[MerkleNode] = Set[MerkleNode]()
  def nodes = _nodes

  def toDot: String = {
    var s: String = ""
    s += "digraph merkle {\n"
    for(edge <- edges) {
      s += s"\t${edge._1.hash.substring(0, 6)} -> ${edge._2.hash.substring(0, 6)}\n"
    }
    s += "}"
    s
  }

  def edges: Set[(MerkleNode, MerkleNode)] = {
    var edges = Set[(MerkleNode, MerkleNode)]()
    for(node <- _nodes) {
      edges = edges ++ node.outgoingEdges
    }
    edges
  }

  def add(n: MerkleNode): Unit = {
    _nodes += n
  }

  def verify(): Boolean = {
    for(node <- nodes) {
      if(node.allBelow().contains(node)) {
        return false
      }
    }
    return true
  }
}

class MerkleNode {
  val name: String = math.random.toString
  lazy val hash: String = name.md5.hex

  private var _incoming: Set[MerkleNode] = Set[MerkleNode]()
  def incoming = _incoming
  def incomingEdges: Set[(MerkleNode, MerkleNode)] = {
    for(node <- incoming) yield {
      Tuple2(node, this)
    }
  }

  private var _outgoing: Set[MerkleNode] = Set[MerkleNode]()
  def outgoing = _outgoing
  def outgoingEdges: Set[Edge] = {
    for(node <- incoming) yield {
      Tuple2(node, this)
    }
  }

  def allBelow(discovered: mutable.Set[MerkleNode] = mutable.Set()): mutable.Set[MerkleNode] = {
    for(node <- outgoing) {
      if(!discovered.contains(node)) {
        discovered += node
        discovered ++= node.allBelow(discovered)
      }
    }
    discovered
  }

  override def toString: String = hash

  def connect(n2: MerkleNode) = {
    _outgoing += n2
    n2._incoming += this
  }
}

object MerkleDAG {
  var counter = 0

  type Edge = (MerkleNode, MerkleNode)

  def main(args: Array[String]): Unit = {
  }
}