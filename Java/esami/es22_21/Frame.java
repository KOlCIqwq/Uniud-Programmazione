package esami.es22_21;

class Frame{
          private int stato;
          private Node sin;
          private Node des;

          public int getState(){
                    return stato;
          }

          public void setState(int val){
                    stato = val;
          }
          public Node getSin(){
                    return sin;
          }

          public void setSin(Node val){
                    sin = val;
          }

          public Node getDes(){
                    return des;
          }

          public void setDes(Node val){
                    des = val;
          }

          public Frame(){
                    stato = 0;
                    sin = null;
                    des = null;
          }
}