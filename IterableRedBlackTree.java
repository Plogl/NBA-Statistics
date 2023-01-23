import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class IterableRedBlackTree<T extends Comparable<T>> extends RedBlackTree<T> implements IterableTreeRBT<T> {

    private int currentSize;

    public static class DBS{
        String name;

        Integer rbs;

        Integer asts;

        Integer steals;

        Integer blocks;

        Integer pts;
    public DBS(String name, Integer rbs, Integer asts, Integer steals, Integer blocks, Integer pts) {
        this.name = name;
        this.rbs = rbs;
        this.asts = asts;
        this.steals = steals;
        this.blocks = blocks;
        this.pts = pts;
    }

    }
    public IterableRedBlackTree(){};
    //consturctor and duplicate a new tree

    public boolean remove() {
        return false;
    }
    //I have emailed to you that we want to change our ways to solution problems.
    @Override
    public Iterator<T> iterator() {
        return null;
    }
    public Node<T> renew(T data, Node<T> root,T newvalue) {//Thou here is for renewing the name, but it also is a index for the store map.

        //Node<T> nt = root;
        //this.RedBlackTree = root;
        if (root == null) {
            // we are at a null child, value is not in tree
            throw new RuntimeException("There is not a same value node");
        } else {
            int compare = data.compareTo(root.data);
            if (compare < 0) {
                // go left in the tree
                return renew(data, root.leftChild, newvalue);
            } else if (compare > 0) {
                // go right in the tree
                return renew(data, root.rightChild, newvalue);
            } else {
                // we found it :)
                root.data= newvalue;
                return root;
            }
        }
    }

    public ArrayList filter(T BiggerEqual, T SmallerEqual, Node<T> root){//这里为了测试代码，先使用int limit
            //TODO ToInorder遍历代码;
        ArrayList output = new ArrayList();
        if (root != null) {//delete this
            Node<T> q = root;
            if(q.leftChild != null || q.rightChild != null) {
                if (q.data.compareTo(BiggerEqual)<=0 && q.data.compareTo(SmallerEqual)>=0){
                	if(q.leftChild != null) {
                		output.addAll(filter(BiggerEqual, SmallerEqual, q.leftChild));
                	}
                	if(q.rightChild != null) {
                		output.addAll(filter(BiggerEqual,SmallerEqual, q.rightChild));
                	}
                    output.add(q.playerData);
                    //This is for multiple nodes(will upgrade later)
                }
                else if(q.data.compareTo(BiggerEqual)>=0 && q.data.compareTo(SmallerEqual)>=0) {
                	if(q.leftChild != null) {
                		output.addAll(filter(BiggerEqual, SmallerEqual, q.leftChild));
                	}
                }
                else if(q.data.compareTo(BiggerEqual)<=0 && q.data.compareTo(SmallerEqual)<=0){
                	if(q.rightChild != null) {
                		output.addAll(filter(BiggerEqual,SmallerEqual, q.rightChild));
                	}
                }

            }
            else if (q.data.compareTo(BiggerEqual)<=0 && q.data.compareTo(SmallerEqual)>=0){
            	output.add(q.playerData);
            }
        }
        return output;
    }
    
    
	@Override
	public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
		
		return false;
	}
}



