package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import datastructures.BinaryTree;
import datastructures.BinaryTreeNode;
import datastructures.DefaultBTN;
import datastructures.LinkedList;
import parsing.QuestionFileReader;
import parsing.QuestionsTree;

/**
 * Controls and produces the GUI for the 20 questions game
 * 
 * @author emnob
 *
 */
public class GameController extends JPanel {
	private QuestionFileReader reader = new QuestionFileReader();
	private LinkedList<String> answers;
	private QuestionsTree tree;
	private BinaryTreeNode<String> currNode;
	private BinaryTreeNode<String> parent;
	private JLabel question;
	public final String INSTRUCTIONS = "Pick an art movement and then answer the questions based on that movement.";

	/**
	 * Constructor for the Game Controller that sets up the game
	 * 
	 * @param file
	 */
	public GameController(String file) {
		// set up layout
		super();
		setLayout(new BorderLayout());
		// initialize variables
		tree = reader.getTree(file);
		currNode = tree.getQuestions().getRoot();
		question = new JLabel(currNode.getData());
		// set up components
		question.setFont(new Font(question.getFont().getName(), Font.PLAIN, 14));
		createTopPanel();
		add(new ImageController(), BorderLayout.CENTER);
		createBottomPanel();

	}

	/**
	 * Makes the top part of the GUI panel
	 */
	private void createTopPanel() {
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		JLabel iLabel = new JLabel(INSTRUCTIONS);
		iLabel.setFont(new Font(iLabel.getFont().getName(), Font.PLAIN, 14));
		top.add(iLabel, BorderLayout.NORTH);
		createMovementList(top);
		add(top, BorderLayout.NORTH);
	}

	/**
	 * Makes the bottom part of the GUI panel
	 */
	private void createBottomPanel() {
		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		bottom.add(question);
		addButtons(bottom);
		add(bottom, BorderLayout.SOUTH);
	}

	/**
	 * Adds a list of the answers to the panel
	 * 
	 * @param panel
	 *            the panel that the list will be added to
	 */
	private void createMovementList(JPanel panel) {
		answers = getAnswers(tree);
		JPanel grid = new JPanel(new GridLayout(answers.size() / 2 + 1, 2));
		while (!answers.isEmpty()) {
			String answerText = answers.getFirst();
			JLabel answer = new JLabel(answerText);
			answer.setFont(new Font(answer.getFont().getName(), Font.PLAIN, 12));
			grid.add(answer);
			answers.deleteFirst();
		}
		panel.add(grid);
		answers = getAnswers(tree);
	}

	/**
	 * Adds yes, no, and reset buttons to the panel
	 * 
	 * @param panel
	 *            the panel that the buttons will be added to
	 */
	private void addButtons(JPanel panel) {
		JPanel button = new JPanel();
		JButton yes = new JButton("Yes");
		// add yes button
		button.add(yes);
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currNode.isLeaf()) {
					question.setText("I win! I guessed correctly. Press restart to play again.");
				} else {
					parent = currNode;
					currNode = currNode.getRightChild();
					if (currNode.isLeaf()) {
						String text = "Is it " + currNode.getData() + "?";
						question.setText(text);
					} else {
						question.setText(currNode.getData());
					}
				}
				validate();
				repaint();
			}

		});

		JButton no = new JButton("No");
		// add no button
		button.add(no);
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currNode.isLeaf()) {
					question.setText("You win. I didn't know what it was. Press restart to play again.");
					enterNewMovement();
				} else {
					parent = currNode;
					currNode = currNode.getLeftChild();
					if (currNode.isLeaf()) {
						String text = "Is it " + currNode.getData() + "?";
						question.setText(text);
					} else {
						question.setText(currNode.getData());
					}
				}
				validate();
				repaint();
			}
		});

		JButton reset = new JButton("Reset");
		button.add(reset);
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currNode = tree.getQuestions().getRoot();
				question.setText(currNode.getData());
				validate();
				repaint();
			}
		});
		panel.add(button);
	}

	/**
	 * Gets a list of the answers currently in the tree
	 * 
	 * @param qtree
	 *            the tree that you want to get answers from
	 * @return a linked list of the answers
	 */
	private LinkedList<String> getAnswers(QuestionsTree qtree) {
		LinkedList<String> list = new LinkedList<>();
		BinaryTree<String> tree = qtree.getQuestions();
		traverse(tree.getRoot(), list);
		return list;
	}

	/**
	 * Traverses the tree recursively to find answers and add them to the list
	 * 
	 * @param node
	 *            the current node you're looking through
	 * @param list
	 *            the list that the answers will be added to
	 */
	private void traverse(BinaryTreeNode<String> node, LinkedList<String> list) {
		if (node == null) {
			return;
		} else if (node.isLeaf()) {
			list.insertLast(node.getData());
		} else {
			traverse(node.getLeftChild(), list);
			traverse(node.getRightChild(), list);
		}
	}
	
	/**
	 * Creates a window that allows the user to add a new answer to the game
	 */
	private void enterNewMovement(){
		JTextField name = new JTextField();
		JTextField question = new JTextField();
		JTextField answer = new JTextField();
		Object[] message = {
			    "New answer name:", name,
			    "Defining question that would determine the answer:", question,
			    "Answer to the defining question (yes or no):", answer,
			};
		int option = JOptionPane.showConfirmDialog(getParent(), message, "Add your answer to the game!",
				JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String newName = name.getText();
			String newQuestion = question.getText();
		    String newAnswer = answer.getText();
		    addNewQuestion(newName, newQuestion, newAnswer);
		}
	}

	/**
	 * Adds a new question to the game tree
	 * @param newName the name of the new thing to be added
	 * @param newQuestion the new question that will determine the answer
	 * @param newAnswer the yes or no answer to the question
	 */
	private void addNewQuestion(String newName, String newQuestion, String newAnswer) {
		boolean yesNode = parent.getRightChild().equals(currNode);
		BinaryTreeNode<String> newNode = new DefaultBTN<>(newQuestion);
		if(newAnswer.toLowerCase().equals("yes")){
			newNode.setRightChild(new DefaultBTN<String>(newName));
			newNode.setLeftChild(currNode);
		} else {
			newNode.setLeftChild(new DefaultBTN<String>(newName));
			newNode.setRightChild(currNode);
		}
		if(yesNode){
			parent.setRightChild(newNode);
		} else {
			parent.setLeftChild(newNode);
		}
	}
}
