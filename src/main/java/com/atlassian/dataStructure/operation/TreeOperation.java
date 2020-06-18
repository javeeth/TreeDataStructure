package com.atlassian.dataStructure.operation;

import com.atlassian.dataStructure.abstractions.ITreeOperations;
import com.atlassian.dataStructure.entities.Node;

import java.util.ArrayList;
import java.util.Objects;

public class TreeOperation implements ITreeOperations {

    @Override
    public <T> ArrayList<T> getAncestors(Node<T> node){

        ArrayList<T> parent = new ArrayList<>();

        if(node == null) {
            return parent;
        }

        if(node.getParent() != null) {
            parent.add(node.getParent().getContent());
            parent.addAll(getAncestors(node.getParent()));
        }
        return parent;
    }

    @Override
    public <T> Node<T> findNode(T data, Node<T> root){

        Node<T> node = null;

        if(root == null) {
            return node;
        }

        if(root.getContent().equals(data))
            return root;

        if(root.getChildren() != null) {
            for(Node<T> child : root.getChildren()){
                node = findNode(data, child);
                if(node != null)
                    return node;
            }
        }
        return node;
    }

    @Override
    public <T> void printLineage(Node<T> root){

        if(root == null) {
            System.out.println("Root node is Empty !!!!!!");
        }

        System.out.println("Node Data  : " + root.getContent());

        if (root.getChildren() == null) {
            System.out.println("Node doesn't have any children");
        } else {
            root.getChildren().stream().filter(Objects::nonNull).forEach(tNode -> System.out.println(tNode.getContent() + " is a child of " + root.getContent()));
        }

        System.out.println("===============================");

        if(root.getChildren() != null) {
            for(Node<T> child : root.getChildren()){
                printLineage(child);
            }
        }
    }

    @Override
    public <T> void deleteNode(Node<T> node){

        if(node.getParent() == null){
            System.out.println("Cannot Delete Root Node");
            return;
        }

        Node<T> parent = node.getParent();

        if(node.getChildren() != null){
            for(Node node1 : node.getChildren()){
                parent.addChild(node1);
            }
        }
        parent.getChildren().remove(node);
        System.out.println("Node Deleted " + node.getContent());
    }

    @Override
    public <T> Integer getDepth(Node<T> node){

        Integer depth = 0;

        if(node == null) {
            return null;
        }
        if(node.getParent() == null) {
            return depth;
        }

        if(node.getParent() != null) {
            depth += 1;
            depth = depth + getDepth(node.getParent());
        }
        return depth;
    }

    public Integer sumOfAllNode(Node<Integer> root){
        Integer sum = 0;

        if(root == null) {
            System.out.println("Root node is Empty !!!!!!");
            return sum;
        }

        sum += root.getContent();

        if(root.getChildren() != null) {
            for(Node<Integer> child : root.getChildren()){
                sum = sum + sumOfAllNode(child);
            }
        }

        return sum;

    }

    public Integer findMax(Node<Integer> root){

        Integer highest, childContent;

        if(root == null) {
            System.out.println("Root node is Empty !!!!!!");
            return null;
        }

        highest = root.getContent();

        if(root.getChildren() != null) {
            for(Node<Integer> child : root.getChildren()){

                childContent = findMax(child);

                if (childContent > highest)
                    highest = childContent;
            }
        }
        return highest;

    }

}
