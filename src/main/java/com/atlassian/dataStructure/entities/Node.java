package com.atlassian.dataStructure.entities;

import com.atlassian.dataStructure.abstractions.INode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Node<T> implements INode<T> {

    private T content;
    private List<Node<T>> children;
    private Node<T> parent;

    public Node(T content) {
        this.content = content;
    }

    @Override
    public Node<T> addChild(Node<T> child) {
        if (this.children == null)
            this.children = new ArrayList<>();
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    @Override
    public void updateNodeContent(T newContent){
        this.content = newContent;
    }
}
