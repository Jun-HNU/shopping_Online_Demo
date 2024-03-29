//https://blog.csdn.net/sheepmu/article/details/38407221
class TreeNode
{
	int value;
	TreeNode parent;
	TreeNode left;
	TreeNode right;
	public TreeNode(int value, TreeNode parent, TreeNode left, TreeNode right) {
		this.value = value;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}


	//求BST的最小值
	public TreeNode  getMin(TreeNode root)
	{
		if(root==null)
			return null;
		while(root.left!=null)
			root=root.left;
		return root;
	}
	//求BST的最大值
	public TreeNode  getMax(TreeNode root)
	{
		if(root==null)
			return null;
		while(root.right!=null)
			root=root.right;
		return root;
	}

	//查找BST中某节点的前驱节点.即查找数据值小于该结点的最大结点。
	public TreeNode preNode(TreeNode x)
	{
		if(x==null)
			return null;
		// 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
		if(x.left!=null)
			return getMax(x.left);
		// 如果x没有左孩子。则x有以下两种可能：
		// (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
		// (02) x是"一个左孩子"，则 前驱节点为x的某一个祖先节点的父节点，而且该祖先节点是作为其父节点的右儿子
		TreeNode p=x.parent;
		while(p!=null&&p.left==x)
		{
			x=p;//父节点置为新的x
			p=p.parent;  //父节点的父节点置为新的父节点
		}
		return p;
	}

	//查找BST中某节点的后继节点.即查找数据值大于该结点的最小结点。
	public TreeNode postNode(TreeNode x)
	{
		if(x==null)
			return null;
		// 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
		if(x.left!=null)
			return getMin(x.right);
		// 如果x没有右孩子。则x有以下两种可能：
		//  (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
		// (02) x是"一个右孩子"，则 前驱节点为x的某一个祖先节点的父节点，而且该祖先节点是作为其父节点的左儿子
		TreeNode p=x.parent;
		while(p!=null&&p.right==x)
		{
			x=p;//父节点置为新的x
			p=p.parent;  //父节点的父节点置为新的父节点
		}
		return p;
	}

	//查找值为val的节点  --递归版--
	public TreeNode searchRec(TreeNode root ,int val)
	{
		if(root==null)
			return root;
		if(val<root.value)
			return searchRec(root.left,val);
		else if(val>root.value)
			return searchRec(root.right,val);
		else
			return root;
	}
	//查找值为val的节点  --非 递归版--
	public TreeNode search(TreeNode root ,int val)
	{
		if(root==null)
			return root;
		while(root!=null)
		{
			if(val<root.value)
				root=root.left;
			else if(val>root.value)
				root=root.right;
			else
				return root;
		}
		return root;
	}



	//BST插入节点  --递归版--
	public TreeNode insertRec(TreeNode root,TreeNode x)
	{
		if(root==null)
			root=x;
		else if(x.value<root.value)
			root.left=insertRec(root.left,  x);
		else if(x.value>root.value)
			root.right=insertRec(root.right,  x);
		return root;
	}
	//BST插入节点  --非 递归版--
	public TreeNode insert(TreeNode root,TreeNode x)
	{
		if(root==null)
			root=x;
		TreeNode p=null;//需要记录父节点
		while(root!=null)//定位插入的位置
		{
			p=root;//记录父节点
			if(x.value<root.value)
				root=root.left;
			else
				root=root.right;
		}
		x.parent=p;//定位到合适的页节点的空白处后，根据和父节点的大小比较插入合适的位置
		if(x.value<p.value)
			p.left=x;
		else if(x.value>p.value)
			p.right=x;
		return root;
	}
	//BST删除节点
	public void delete(TreeNode root,TreeNode x)
	{
		if(root==null)
			return ;
		TreeNode p=null;
		while(root!=null)//定位到需要删除的节点
		{
			if(x.value<root.value)
			{
				p=root;//记录父节点
				root=root.left;
			}
			else if(x.value>root.value)
			{
				p=root;//记录父节点
				root=root.right;
			}
			else//找到啦
			{
				if(root.left==null&&root.right==null)//①待删除的是 叶子节点
				{
					if(p==null)//待删除的是根节点
						root=null;
					else
					{
						if(p.left==root)
							p.left=null;
						else if(p.right==root)
							p.right=null;
					}
				}
				else if(root.left!=null&&root.right==null)//② 待删除的节点只有左孩子
				{
					if(p==null)//待删除的是根节点
						root=root.left;
					else
					{
						if(p.left==root)//待删除的本身是一个左孩子
							p.left=root.left;
						else if(p.right==root)
							p.right=root.left;
					}
				}
				else if(root.left==null&&root.right!=null)//② 待删除的节点只有右孩子
				{
					if(p==null)//待删除的是根节点
						root=root.right;
					else
					{
						if(p.left==root)//待删除的本身是一个左孩子
							p.left=root.right;
						else if(p.right==root)
							p.right=root.right;

					}
				}
				else//③待删除的节点即有左孩子又有右孩子    方法：得到待删除节点右子树的最小值，
				{//该最小值与待删除节点进行“ 值 ”交换，删除该最小值位置处的节点
					TreeNode rMin=root.right; //求待删除节点的后继节点,即待删除节点的右孩子的最小值(找到的后继节点肯定没有左孩子！！！)
					TreeNode rMinP=null;//因为需要删除后继节点位置，所以需要记录父节点
					while(rMin!=null)
					{
						rMinP=rMin;
						rMin=rMin.left;
					}
					int rootVtemp=root.value;//值交换
					root.value=rMin.value;
					rMin.value=rootVtemp;
					//删除rMin位置的节点，此时此位置的值已是待删节点的值
					if(rMinP.left==rMin)
						rMinP.left=rMin.right;
					else if(rMinP.right==rMin)
						rMinP.right=rMin.right;
				}
			}
			break;//找到后删了后就跳出while循环
		}

	}

	public static void main(String[] args)
	{
		int[] a={5,7,6,9,11,10,8};
		int len=a.length;

		System.out.println(isProOfBST(a,len));
	}
	public static boolean isProOfBST(int[] a,int len)
	{
		if(a==null||len<=0)
			return false;
		int root=a[len-1];//后序遍历的最后一个为根节点
		int i=0;
		while(a[i]<root)//找到左树的个数
			i++;
		int j=i;//先看右树中是否有非法数字，即比根节点小的数字
		while(j<len-1)
		{
			if(a[j]<root)
				return false;
			j++;
		}
		//若左右子树的数字都合法，即左子树都比根的值小，右子树都比根节点大;此时只需递归判断左右子树是否是二叉搜索树的后序遍历
		//求左右子树的数组，到这儿明显发现用字符串很爽呀直接subString()
		boolean left=true;
		if(i>0)//必须要判断是否存在左树
		{
			int[] aleft=new int[i];
			for(int x=0;x<i;x++)
				aleft[x]=a[x];
			left=isProOfBST(aleft,i);
		}
		boolean right=true;
		if(i<len-1)//必须要判断是否存在右树
		{
			int[] aright=new int[len-i-1];
//			for(int y=i;y<len-1;y++)//粗心啊！！！！
//			{
//				aright[y]=a[y];
//			}
			for(int y=0;y<len-i-1;y++)
				aright[y]=a[i+y];
			right=isProOfBST(aright,len-i-1);
		}
		return left&&right;
	}

}

public class BinarySearchTree{
  

} 
