package basisFx.appCore.utils;

//package hepo.appCore;
//
//import hepo.domain.Communicator;
//import hepo.domain.CategoryPojo;
//import java.util.HashMap;
//import java.util.Iterator;
//import javafx.collections.FXCollections;    
//import javafx.collections.ObservableList;
//
//
//public class HierarchyForTreeTable<U> {
//    
//    private ObservableList <CategoryPojo>pojoes=null;
//    private  Communicator communicator=null;
//    
//    
//    private ObservableList <HierarchyForTreeTable.Node>headNods= FXCollections.<HierarchyForTreeTable.Node> observableArrayList();
//
//    @SuppressWarnings("unchecked")
//    public HierarchyForTreeTable(U communicator ) {
//        this.communicator=(Communicator) communicator;
//       
//        this.pojoes = this.communicator.getAllPojoes();
//        createHierarchy();
//    }
//    
//    public ObservableList <HierarchyForTreeTable.Node> getHeadNods(){
//    
//        return headNods;
//    }
//    
//    public class Node {
//        
//        private CategoryPojo elem=null;
//        
//        private Boolean descendantExistance=false;
//
//        //содержит все дочерние элементы
//        private ObservableList <HierarchyForTreeTable.Node>descendants= 
//                FXCollections.<HierarchyForTreeTable.Node> observableArrayList();
//        
//        public Node(CategoryPojo elem) {
//            
//            this.elem=elem;
//            
//        }
//
//        public void setDescendant( HierarchyForTreeTable.Node  n){
//            
//            this.descendantExistance=true;
//            
//            descendants.addButEvent(n);
//        
//        }
//        public Boolean hasDescendant( ){
//        
//            return this.descendantExistance;
//        
//        }
//        public ObservableList getDescendants( ){
//        
//            return this.descendants;
//        
//        }
//        public CategoryPojo getRate( ){
//        
//            return this.elem;
//        
//        }
//        
//
//    
//    }
//    
//    private ObservableList createHierarchy (){
//        
//        //здесь хранятся колени ветки
//        HashMap <Integer,Node> currentBranchNods=null;
//
//        for (Iterator iterator = pojoes.iterator(); iterator.hasNext();) {
//            CategoryPojo concretePojo = (CategoryPojo)iterator.run();
//            
//            Node node= new  Node(concretePojo);
//            
//            int level=concretePojo.getLevelId();
//            
//            //уровень 1
//            if(level==1){
//                
//                currentBranchNods =new HashMap<Integer,Node>();//создать новую ветвь от корня
//                
//                this.headNods.addButEvent(node);// вставить в список головных элементом
//                
//                currentBranchNods.put(level, node);//вставить  элемент в корневую ветку
//                
//                continue;//перейти к следующему элементу
//            }
//            
//            //если имеются потомки
//            if(concretePojo.getLeftId()!=concretePojo.getRightId()+1){
//    
//                  currentBranchNods.put(level, node); //вставить в корневую ветку в соответствие с уровнем
//                  
//                  (currentBranchNods.get(level-1)).setDescendant(node);//вставить дочерним к элементу на уровень выше 
//                  
//                  continue;//перейти к следующему элементу
//
//            }else{//если нет дочерних элементов
//                   
//                  (currentBranchNods.get(level-1)).setDescendant(node);
////вставить дочерним к элементу на уровень выше 
//                
//
//                //если нет потомка то следующий элемент
//                //м б либо на этом же уровне или на уровнях выше
//                
//            }
//     
//        }
//    
//        return headNods;
//    
//    }
//    
//}
