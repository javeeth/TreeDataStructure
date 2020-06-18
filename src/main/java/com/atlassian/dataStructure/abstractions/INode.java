package com.atlassian.dataStructure.abstractions;

import com.atlassian.dataStructure.entities.Node;

import java.util.ArrayList;

public interface INode<T> {

    Node<T> addChild(Node<T> child);
    void updateNodeContent(T newContent);
}
