//Linked List Practice

public class Node {
    
    var value: Int
    var next: Node?
    
    init(value: Int) {
        
        self.value = value
    }
}

public class LinkedList {
    
    fileprivate var head: Node?
    
    public var isEmpty: Bool {
        return head == nil
    }
    
    public var first: Node? {
        return head
    }
    
    //Append To The Beginning of the List
    public func append(insertAtBeginning value: Int) {
        
        let newNode = Node(value: value)
        
        newNode.next = head
        head = newNode
    }
    
    //Append To The End of the List
    public func append(inesertAtEnd value: Int) {
        
        let newNode = Node(value: value)
        
        if !isEmpty {
            
            var temp = head
            
            while temp?.next != nil {
                
                temp = temp?.next
            }
            
            temp?.next = newNode
        }
    }
    
    //Print All Elements of The List (Recursive Call)
    public func printLinkedlikes(node: Node?) {
        
        let temp = node
        
        if isEmpty {
            
            fatalError("No data found in the list")
            
        } else if temp != nil {
            
            print(temp?.value ?? "")
            
            printLinkedlikes(node: temp?.next)
        }
    }
}

let numebrsList = LinkedList()

numebrsList.append(insertAtBeginning: 100)
numebrsList.append(insertAtBeginning: 200)
numebrsList.append(insertAtBeginning: 300)
numebrsList.append(insertAtBeginning: 700)

numebrsList.append(inesertAtEnd: 500)
numebrsList.append(inesertAtEnd: 400)

numebrsList.printLinkedlikes(node: numebrsList.head)
