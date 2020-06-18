package com.atlassian.dataStructure.abstractions;

import com.atlassian.dataStructure.entities.Node;

import java.util.ArrayList;

public interface ITreeOperations {

    <T> ArrayList<T> getAncestors(Node<T> node);
    <T> Node<T> findNode(T data, Node<T> root);
    <T> void printLineage(Node<T> root);
    <T> void deleteNode(Node<T> root);
    <T> Integer getDepth(Node<T> node);
}
