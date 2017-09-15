package parsing;
import datastructures.BinaryTree;
import datastructures.BinaryTreeNode;
import datastructures.DefaultBT;


/**
 * Class that holds and manages the parsed question tree
 * 
 * @author Emilia Nobrega
 *
 */
public class QuestionsTree {
	private BinaryTree<String> questionTree;

	/**
	 * Constructor for the Questions tree
	 */
	public QuestionsTree() {
		questionTree = new DefaultBT<String>();
	}

	/**
	 * Adds the root question to the tree to build the tree itself
	 * @param question
	 */
	public void addSeedQuestion(BinaryTreeNode<String> question){
		questionTree.setRoot(question);
	}
	
	/**
	 * Converts the tree to a string representation
	 * @return an in-order traversal of the tree
	 */
	public String toString() {
		return questionTree.inorderTraversal().toString();
	}

	/**
	 * gets the question tree
	 * @return the question tree
	 */
	public BinaryTree<String> getQuestions() {
		return questionTree;
	}

}
