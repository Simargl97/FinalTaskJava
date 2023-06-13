package FinalTask;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T, N extends TreeNode<T, N>> {

    T data;
    ArrayList<N> children;
    N parent;

    public TreeNode() {

    }

    public TreeNode(T data, ArrayList<N> children, N parent) {
        this.data = data;
        this.children = children;
        this.parent = parent;
    }

    public static <X, Y> Y fold(List<X> list, Y initValue, Func2<X, Y, Y> foldFunction) {
        Y result = initValue;
        for (X item : list) {
            result = foldFunction.apply(item, result);
        }
        return result;
    }


    public interface TypeAdapter<T, N> {
        N newInstance();

        boolean isChildOf(T parentNodeData, T childNodeData);

        boolean isTopLevelItem(T data);
    }

    public static <T, N extends TreeNode<T, N>> void makeTree(List<T> datas, TypeAdapter<T, N> typeAdapter) {
        N root = typeAdapter.newInstance();

        root.children = new ArrayList<>();
        for (T top : FuncUtils.filter(datas, typeAdapter::isTopLevelItem)) {
            root.children.add(extractNode(top, root, datas, typeAdapter));
        }
    }

    protected static <T, N extends TreeNode<T, N>> N extractNode(T data, N parent, List<T> datas, TypeAdapter<T, N> typeAdapter) {
        N node = typeAdapter.newInstance();
        node.data = data;
        node.parent = parent;

        List<T> directChildren = FuncUtils.filter(datas, d -> typeAdapter.isChildOf(data, d));
        if (!directChildren.isEmpty()) {
            node.children = new ArrayList<>();
            for (T child : directChildren) {
                node.children.add(extractNode(child, node, datas, typeAdapter));
            }
        }
        return node;
    }

}


