package cn.hnx;

import java.util.Set;

/**
 * Created by viruser on 2019/8/8.
 */
public class NiceHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    private Entry[] table;

    private int size = 0;

    private Set<K> keySet;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public NiceHashMap() {
        table = new Entry[DEFAULT_CAPACITY];
    }

    public NiceHashMap(int capacity) {
        table = new Entry[capacity];
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public Set<K> getKeySet() {
        return this.keySet;
    }



    public Object get(K key){
        //通过key,求hash值
        int hashValue = this.hash(key);
        //通过hash,找到这个key应该放的位置
        int i = this.getIndex(hashValue, this.table.length);
        for(Entry entry = this.table[i]; entry != null; entry = entry.next){

            if (entry.hash == hashValue &&
                    (entry.key == key || key.equals(entry.key))){
                return entry.value;
            }
        }

        return null;
    }



    public V put(K key, V value){
        //通过key,求hash值
        int hashValue = this.hash(key);
        //通过hash,找到这个key应该放的位置
        int i = this.getIndex(hashValue, this.table.length);
        //i位置已经有数据了，往链表添加元素

        for(Entry entry = this.table[i]; entry != null; entry = entry.next){

            if (entry.hash == hashValue &&
                    (entry.key == key || key == null || key.equals(entry.key))){
                V oldValue = (V) entry.value;
                entry.value = value;
                return oldValue;
            }
        }

        this.addEntry(key, value, hashValue, i);

        return null;
    }


    public void addEntry(K key, V value, int hashValue, int i){

        if (++size >= this.table.length * DEFAULT_LOAD_FACTOR){
            Entry[] newTable = new Entry[this.table.length * 2];
            System.arraycopy(this.table,0, newTable,0, this.table.length);
            this.table = newTable;
        }

        //的到i位置的数据
        Entry eNode = table[i];
        //新增节点，将该节点的next指向前一个节点
        table[i]=new Entry(hashValue,key,value,eNode);
    }


    public int getIndex(int hashValue,int length){
        return hashValue%length;
    }

    public int hash(K key){
        return key.hashCode();
    }


    public static class Entry<K, V>{

        private int hash;

        private K key;

        private V value;

        private Entry next;

        public Entry(int hash, K key, V value, Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }
    }
}
