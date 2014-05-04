package demos.algorithm;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class VList<T> extends AbstractList<T> {
	private static final class VListCell<T>{
		public final T[] mElems;
		public final VListCell<T> mNext;
		public VListCell<T> mPrev;
		public int mFreeSpace;
		@SuppressWarnings("unchecked")
		public VListCell(int numElems, VListCell<T> next){
			mElems = (T[]) new Object[numElems];
			mNext = next;
			mPrev = null;
			
			if(next != null){
				next.mPrev = this;
			}
			
			mFreeSpace = numElems;
		}
	}
	
	private static final class VListLocation<T>{
		public final VListCell<T> mCell;
		public final int mOffset;
		
		public VListLocation(VListCell<T> cell, int offset){
			mCell = cell;
			mOffset = offset;
		}
	}
	
	private VListCell<T> mHead;
	private int mSize;
	
	@Override
	public boolean add(T e) {
		if(mHead == null || mHead.mFreeSpace ==0){
			mHead = new VListCell<>(mHead == null ? 1: mHead.mElems.length * 2, mHead);
		}
		
		mHead.mElems[(mHead.mFreeSpace--) - 1] = e;
		++mSize;
		
		return true;
	}
	
	private VListLocation<T> locateElement(int index){
		if(index >= size() || index < 0){
			throw new IndexOutOfBoundsException("Position " + index + "; Size " + size());
		}
		
		index = size() - 1;
		VListCell<T> curr = mHead;
		while(index >= curr.mElems.length - curr.mFreeSpace){
			index -= curr.mElems.length - curr.mFreeSpace;
			curr = curr.mNext;
		}
		return new VListLocation<>(curr, index + curr.mFreeSpace);
		
	}
	
	@Override
	public T get(int index) {
		VListLocation<T> where = locateElement(index);
		return where.mCell.mElems[where.mOffset];
	}
	
	@Override
	public int size() {
		return mSize;
	}
	
	@Override
	public T set(int index, T element) {
		VListLocation<T> where = locateElement(index);
		T result = where.mCell.mElems[where.mOffset];
		where.mCell.mElems[where.mOffset] = element;
		return result;
	}
	
	@Override
	public T remove(int index) {
		VListLocation<T> where = locateElement(index);
		T result = where.mCell.mElems[where.mOffset];
		removeAtPosition(where);
		return result;
	}
	
	private void removeAtPosition(VListLocation<T> where){
		VListCell<T> curr = where.mCell;
		for(int shuffleTargetPosition = where.mOffset; curr != null; 
				curr= curr.mPrev, shuffleTargetPosition = (curr == null ? 0 : curr.mElems.length - 1)){
			for(int i = shuffleTargetPosition - 1; i >= 0; --i)
				curr.mElems[i + 1] = curr.mElems[i];
			
			if(curr.mPrev != null){
				curr.mElems[0] = curr.mPrev.mElems[curr.mPrev.mElems.length - 1];
			}
		}
		++mHead.mFreeSpace;
		mHead.mElems[mHead.mFreeSpace - 1] = null;
		
		--mSize;
		
		if(mHead.mFreeSpace == mHead.mElems.length){
			mHead = mHead.mNext;
			if(mHead != null){
				mHead.mPrev = null;
			}
		}
	}
	
	private final class VListIterator implements Iterator<T>{
		private VListCell<T> mCurrCell;
		private int mCurrIndex;
		private boolean mCanRemove;
		
		public VListIterator(){
			VListCell<T> curr, prev;
			for(curr = mHead, prev = null; curr != null; prev = curr, curr = curr.mNext)
				;
			
			mCurrCell = prev;
			
			if(mCurrCell != null)
				mCurrIndex = 0;
		}
		
		@Override
		public boolean hasNext() {
			return mCurrCell != null;
		}
		
		@Override
		public T next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			
			T result = mCurrCell.mElems[mCurrIndex];
			
			--mCurrIndex;
			
			if(mCurrIndex < mCurrCell.mFreeSpace){
				mCurrCell = mCurrCell.mPrev;
				if(mCurrCell != null){
					mCurrIndex = mCurrCell.mElems.length - 1;
				}
			}
			mCanRemove = true;
			return result;
		}
		
		@Override
		public void remove() {
			if(!mCanRemove)
				throw new IllegalStateException("remove() without next(), or double remove().");
			
			mCanRemove = false;
			
			if(mCurrCell == null){
				VList.this.remove(size() - 1);
			}else if(mCurrIndex != mCurrCell.mElems.length - 1){
				++mCurrIndex;
				removeAtPosition(new VListLocation<>(mCurrCell, mCurrIndex));
			}else{
				mCurrCell = mCurrCell.mNext;
				mCurrIndex = 0;
				removeAtPosition(new VListLocation<>(mCurrCell, mCurrIndex));
			}
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		return new VListIterator();
	}
}
