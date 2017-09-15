package parsing;
import javax.xml.parsers.*;

import org.xml.sax.SAXException;

import datastructures.BinaryTreeNode;
import datastructures.DefaultBTN;

import org.w3c.dom.*;

import java.io.*;

/**
 * @author Emilia Nobrega
 *
 */
public class QuestionFileReader {

	/**
	 * Main program that takes and reads an xml file and parses it
	 * 
	 * @param args
	 *            the arguments for the main program, put the xml file here
	 */
	public static void main(String[] args) {
		// Setup XML Document
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		File xmlFile = new File(args[0]);
		Document document = null;
		try {
			document = builder.parse(xmlFile);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(parseQuestionFile(document).toString());
	}

	/**
	 * Takes an xml file and reads it
	 * 
	 * @param file
	 *            the xml file to be read
	 * @return the question tree based on that xml file
	 */
	public QuestionsTree getTree(String file) {
		// Setup XML Document
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		File xmlFile = new File(file);
		Document document = null;
		try {
			document = builder.parse(xmlFile);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return parseQuestionFile(document);
	}

	/**
	 * parses the question file document
	 * 
	 * @param document
	 *            the question file document
	 * @return a questions tree object that holds all the information about the
	 *         questions
	 */
	private static QuestionsTree parseQuestionFile(Document document) {
		Node docRoot = document.getDocumentElement();
		QuestionsTree questions = new QuestionsTree();
		parseNode(docRoot, null, questions);
		return questions;
	}

	/**
	 * parses each node in the question file and each of its sub-nodes
	 * 
	 * @param n
	 *            the current node
	 * @param questions
	 *            the questions so far
	 */
	private static void parseNode(Node n, BinaryTreeNode<String> parent, QuestionsTree questions) {
		if (n.getNodeType() == Node.ELEMENT_NODE) {
			Element currentElt = (Element) n;
			String nodeName = currentElt.getNodeName();
			boolean yes = currentElt.getAttribute("answer").equals("yes");

			if (nodeName.equals("thing")) {
				// if it's a thing node add it to the parent make a node from
				// the data. Because this node won't have any children, its the
				// base case in this recursion.
				BinaryTreeNode<String> thingNode = new DefaultBTN<>(currentElt.getTextContent());
				// make it a child based on whether the answer was yes
				if (yes) {
					parent.setRightChild(thingNode);
				} else {
					parent.setLeftChild(thingNode);
				}
			} else if (nodeName.equals("question")) {
				// If it's a Question Node parse the Question
				BinaryTreeNode<String> questionNode = new DefaultBTN<>(currentElt.getAttribute("text"));
				if (parent == null) {
					questions.addSeedQuestion(questionNode);
				} else if (yes) {
					parent.setRightChild(questionNode);
				} else {
					parent.setLeftChild(questionNode);
				}
				// change the parent so that we can give the new node children
				parent = questionNode;
			}
			// check for recursion
			if (currentElt.hasChildNodes()) {
				NodeList nodes = currentElt.getChildNodes();
				for (int i = 0; i < nodes.getLength(); i++) {
					parseNode(nodes.item(i), parent, questions);
				}
			}
		}

	}
}