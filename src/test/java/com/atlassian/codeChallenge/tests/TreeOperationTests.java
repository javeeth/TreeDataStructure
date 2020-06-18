package com.atlassian.codeChallenge.tests;

import com.atlassian.dataStructure.entities.Node;
import com.atlassian.dataStructure.operation.TreeOperation;
import com.google.inject.Inject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

@Guice
public class TreeOperationTests {

    @Inject
    TreeOperation treeOperation;

    @Test
    public void checkNodeIsCreated(){
        Node<Integer> root = new Node<>(0);
        Node<Integer> child1 = root.addChild(new Node<>(1));

        Assert.assertTrue(root != null);
        Assert.assertTrue(root.getParent() == null);
        Assert.assertEquals(root.getContent(), new Integer(0));

        Assert.assertEquals(child1.getParent(), root);
        Assert.assertEquals(child1.getContent(), new Integer(1));

    }

    @Test
    public void checkUpdateNode(){
        Node<Integer> root = new Node<>(0);;

        Assert.assertEquals(root.getContent(), new Integer(0));

        treeOperation.printLineage(root);
        root.updateNodeContent(1);

        treeOperation.printLineage(root);
        Assert.assertEquals(root.getContent(), new Integer(1));

    }

    @Test
    public void checkDeleteNode(){
        Node<Integer> root = new Node<>(0);
        Node<Integer> child1 = root.addChild(new Node<>(1));

        Assert.assertEquals(root.getChildren().size(), 1);

        treeOperation.deleteNode(child1);
        Assert.assertEquals(root.getChildren().size(), 0);

    }

    @Test
    public void checkFindNode(){
        Node<Integer> root = new Node<>(0);
        Node<Integer> child1 = root.addChild(new Node<>(1));

        treeOperation.printLineage(root);
        Assert.assertEquals(root.getChildren().size(), 1);

        Node<Integer> resultNode = treeOperation.findNode(1, root);
        Assert.assertEquals(resultNode.getContent(), new Integer(1));

    }

    @Test
    public void validateGetAncestorMethod() {

        Node<Integer> root = new Node<>(0);
        Node<Integer> child1 = root.addChild(new Node<>(1));
        Node<Integer> child2 = root.addChild(new Node<>(2));
        Node<Integer> child3 = root.addChild(new Node<>(3));
        Node<Integer> child4 = root.addChild(new Node<>(4));
        Node<Integer> child5 = child4.addChild(new Node<>(5));
        Node<Integer> child6 = child5.addChild(new Node<>(6));


        ArrayList<Integer> ancestors = treeOperation.getAncestors(child6);
        ancestors.stream().forEach(s -> System.out.println(s));
        Assert.assertEquals(ancestors, Arrays.asList(5,4,0));

    }

    @Test
    public void validateFindMaximumMethod() {

        Node<Integer> root = new Node<>(0);
        Node<Integer> child1 = root.addChild(new Node<>(1));
        Node<Integer> child2 = root.addChild(new Node<>(2));
        Node<Integer> child3 = root.addChild(new Node<>(3));
        Node<Integer> child4 = root.addChild(new Node<>(400));
        Node<Integer> child5 = child4.addChild(new Node<>(5));
        Node<Integer> child6 = child5.addChild(new Node<>(6));

        Assert.assertEquals(treeOperation.findMax(root), new Integer(400));

    }

    @Test
    public void validateSumOfAllNodeMethod() {

        Node<Integer> root = new Node<>(100);
        Node<Integer> child1 = root.addChild(new Node<>(200));
        Node<Integer> child2 = root.addChild(new Node<>(300));
        Node<Integer> child3 = root.addChild(new Node<>(400));
        Node<Integer> child4 = root.addChild(new Node<>(500));
        Node<Integer> child5 = child4.addChild(new Node<>(600));
        Node<Integer> child6 = child5.addChild(new Node<>(700));

        Assert.assertEquals(treeOperation.sumOfAllNode(root), new Integer(2800));

    }

    @Test
    public void validateDepthOfNodeMethod() {

        Node<Integer> root = new Node<>(100);
        Node<Integer> child1 = root.addChild(new Node<>(200));
        Node<Integer> child2 = root.addChild(new Node<>(300));
        Node<Integer> child3 = root.addChild(new Node<>(400));
        Node<Integer> child4 = root.addChild(new Node<>(500));
        Node<Integer> child5 = child4.addChild(new Node<>(600));
        Node<Integer> child6 = child5.addChild(new Node<>(700));

        Assert.assertEquals(treeOperation.getDepth(child4), new Integer(1));

    }


    @Test
    public void testTreeOperation() {

        Node<Integer> root = new Node<>(0);
        Node<Integer> child1 = root.addChild(new Node<>(1));
        Node<Integer> child2 = root.addChild(new Node<>(2));
        Node<Integer> child3 = root.addChild(new Node<>(3));
        Node<Integer> child4 = root.addChild(new Node<>(4));
        Node<Integer> child5 = child4.addChild(new Node<>(5));
        Node<Integer> child6 = child5.addChild(new Node<>(6));


        ArrayList<Integer> parents = treeOperation.getAncestors(child6);

        parents.stream().forEach(s -> System.out.println(s));

        Node<Integer> findNode = treeOperation.findNode(6, root);

        treeOperation.printLineage(root);

        treeOperation.deleteNode(findNode);

        treeOperation.printLineage(root);

        root.updateNodeContent(12);

        Integer sum = treeOperation.sumOfAllNode(root);

        Integer max = treeOperation.findMax(root);

        Integer depth = treeOperation.getDepth(child5);

    }


}
