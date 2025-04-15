public class OffByN implements CharacterComparator{
    public static int  num;
    public OffByN(int N) {
        num=N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int a=x-y;
        if(a==-num||a==num)return true;
        else return false;
    }

}
