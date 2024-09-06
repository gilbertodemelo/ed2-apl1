public class Queue<T> {


    private class Node<T> {

        T value;
        Node next;
        Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

    } // Node<T>

    // atributos
    private Node front = null;
    private Node rear = null;

    // Construtor(es)

    // setters
    public void setFront(Node node) { front = node; }
    public void setRear(Node node) { rear = node; }

    // getters
    public Node getFront() { return front; }
    public Node getRear() { return rear; }

    // métodos
    // adiciona um valor a queue
    public void enqueue(T val) {
        if (rear != null) {
            rear.next = new Node(val, null);
            rear = rear.next;
        } else {
            rear = new Node(val, null);
            front = rear;
        }
    }

    // vê se a fila está vazia
    public boolean isEmpty() {
        return front == null;
    }

    // retorna o valor na frente da fila (não remove)
    public T peek() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return front.value;

    }

    // remove e retorna o valor na frente da fila
    public T dequeue() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();
        else {
            T value = front.value;
            front = front.next;
            if (front == null)
                rear == null;

            return value;
        }
    }

    // mostra os valores que estão na fila
    public T toString() {
        StringBuilder builder = new StringBuilder();

        Node ptr = front;
        while(ptr != null) {
            builder.append(p.value + " ");
            ptr = ptr.next;
        }
        return builder.toString();
    }


}