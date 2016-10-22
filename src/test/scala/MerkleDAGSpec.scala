import org.scalatest.FlatSpec

class MerkleDAGSpec extends FlatSpec {
  "verify" should "catch cyclic graphs" in {
    val dag = new MerkleDAG()

    val n1 = new MerkleNode()
    val n2 = new MerkleNode()

    n1.connect(n2)
    n2.connect(n1)

    dag.add(n1)
    dag.add(n2)

    assert(!dag.verify())
  }

  "toDot" should "generate validly" in {
    val dag = new MerkleDAG()

    val n1 = new MerkleNode()
    val n2 = new MerkleNode()
    val n3 = new MerkleNode()

    n1.connect(n2)
    n1.connect(n3)
    n2.connect(n3)

    dag.add(n1)
    dag.add(n2)
    dag.add(n3)

    println(dag.toDot)
  }
}
