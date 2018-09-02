package au.edu.holmesglen.hdworkoski.assignment;

public class Item {
    private int colorImg;
    int click = 0;

    public Item(int colImg, String title) {
        this.setColor(colImg);
    }

    public int getColor() {
        return colorImg;
    }

    public void setColor(int position) {
        colorImg = position;
    }

    public void isLost(int position) {
        
    }

    //public int nextColor() {

   //     colorImg = R.drawable.grey;
        //++click;
        //if click value has reached 4 reset it back to 1 (green)
        //if (++click > 3)
        //    click = 1;



    /*        switch (click) {
                case 1:
                    colorImg = R.drawable.green;
                    break;
                case 2:
                    colorImg = R.drawable.blue;
                    break;
                //case 3:
                //    colorImg = R.drawable.grey;
                //    break;
            }

        //else {
            colorImg = R.drawable.blue;
        //}
        return colorImg;
    }

    public int next() {

    }*/
}