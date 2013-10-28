package reception.desk

class CallNumberService {
    static scope = "singleton"

    def getNext() {
          return CallNumber.getNext();
    }
}
