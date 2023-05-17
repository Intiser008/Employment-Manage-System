class actinghead implements Comparable<actinghead>{
    private Day startDate;
    private Day endDate;
    private Employee actingHead;

    actinghead(Employee actingHead, Day startDate, Day endDate){
        this.actingHead = actingHead;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toString(){
        return startDate.toString() + " to " + endDate.toString() + ": " + actingHead.getName();
    }
    @Override
        public int compareTo(actinghead another){
            if (this.startDate.compareTo(another.startDate) == 0){
                if ((this.endDate).compareTo(another.endDate) > 0)
                    return 1;
                else if ((this.endDate).compareTo(another.endDate) < 0)
                    return -1;
                else
                    return 0;
            }
            else if (this.startDate.compareTo(another.startDate) > 0)
                return 1;
            else
                return -1;
        }
}
