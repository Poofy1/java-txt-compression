public class BinaryHeap {
	//implements a binary heap where the heap rule is the value in the parent node is less than
	//or equal to the values in the child nodes
	
	//the implementation uses parallel arrays to store the priorities and the trees.
	int priority[];
	HuffmanTree trees[];
	int size;
	
	public BinaryHeap(int s) {
		priority = new int[s+1];
		trees = new HuffmanTree[s+1];
		size = 0;
	}
	
	public void removeMin() {
		//PRE: size != 0
		//removes the priority and tree at the root of the heap
		trees[1] = trees[size];
		priority[1] = priority[size];
		size--;
		int index = 1;
		int min;
		while(index * 2 <= size) {
			if(priority[index * 2] <= priority[index * 2 + 1]) {
				min = index * 2;
			} else {
				min = index * 2 + 1;
			}
			if(priority[index] > priority[min]) {
				int temp = priority[index];
				priority[index] = priority[min];
				priority[min] = temp;
				HuffmanTree node = trees[index];
				trees[index] = trees[min];
				trees[min] = node;
				index = min;
			} else {
				break;
			}
		}
	}
	
	public int getMinPriority() {
		//PRE: size != 0
		//return the priority in the root of the heap
		return priority[1];
	}
	
	public HuffmanTree getMinTree() {
		//PRE: size != 0
		//return the tree in the root of the heap
		return trees[1];
	}
	
	public boolean full() {
		//return true if the heap is full otherwise return false
		if(size == priority.length - 1) return true;
		return false;
	}
	
	public void insert(int p, HuffmanTree t) {
		//insert the priority p and the associated tree t into the heap
		//PRE !full()
		size++;
		int index = size;
		priority[size] = p;
		trees[size] = t;
		while(index > 1) {
			if(priority[index] < priority[index/2]) {
				int temp = priority[index];
				priority[index] = priority[index/2];
				priority[index/2] = temp;
				HuffmanTree node = trees[index];
				trees[index] = trees[index/2];
				trees[index/2] = node;
				index = index/2;
			} else {
				break;
			}
		}
	}
	
	public int getSize() {
		//return the number of values, (priority , tree) pairs, in the heap
		return size;
	}
}