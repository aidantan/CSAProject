package src;


/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester2
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  public static void testKeepOnlyBlue()
  {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.keepOnlyBlue();
	  beach.explore();
  }
  public static void testKeepOnlyRed()
  {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.keepOnlyRed();
	  beach.explore();
  }
  public static void testKeepOnlyGreen()
  {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.keepOnlyGreen();
	  beach.explore();
  }
  public static void testNegate()
  {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.negate();
	  beach.explore();
  }
  public static void testGrayscale()
  {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.grayscale();
	  beach.explore();
  }
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  public static void testMirrorVerticalRightToLeft()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVerticalRightToLeft();
    caterpillar.explore();
  }
  public static void testMirrorHorizontalBotToTop()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontalBotToTop();
    caterpillar.explore();
  }
  public static void testMirrorHorizontal()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontal();
    caterpillar.explore();
  }
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  public static void testMirrorArms()
  {
	  Picture snowman= new Picture("snowman.jpg");
	    snowman.explore();
	    snowman.mirrorArms();
	    snowman.explore();
  }
  public static void testMirrorGull()
  {
	  Picture gull= new Picture("seagull.jpg");
	    gull.explore();
	    gull.mirrorGull();
	    gull.explore();
  }
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  public static void testMirrorDiagonal()
  {
    Picture swan = new Picture("swan.jpg");
    swan.mirrorDiagonal();
    swan.explore();
  }
  public static void testEdgeDetection2()
  {
      Picture swan = new Picture("swan.jpg");
      //swan.explore();
      swan.edgeDetection2(30);
      swan.explore();
  }
  public static void testFixUnderwater()
  {  Picture water= new Picture("water.jpg");
	  water.explore();
	water.fixUnderwater();
	  water.explore();
  }
  public static void testSharpen(int x, int y, int w, int h)
  {
	  Picture motor = new Picture("redMotorcycle.jpg");
      //swan.explore();
	  motor.explore();
      motor.sharpen(x,y,w,h);
      motor.explore();
  }
  public static void testEncode()
  {
	  Picture beach = new Picture("beach.jpg");
	  Picture message = new Picture("raven.jpg");
	  beach.explore();
	  message.explore();
	  beach.encode(message);
	  System.out.println("cool");
	  beach.explore();
	  beach.decode();
	  beach.explore();
  }
  public static void testDCT() {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.dct();
	  beach.explore();
	 // beach.idct();
	//  beach.explore();
	  
  }
  

  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
testDCT();
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();  //done
	  // testKeepOnlyBlue(); //done
   // testKeepOnlyRed();  //done
    //testKeepOnlyGreen();  //done
    //testNegate(); //done
	  //testFixUnderwater();
    //testGrayscale(); //done
   // testMirrorVertical(); //done
	// testMirrorVerticalRightToLeft(); //done
	 // testMirrorHorizontal(); //done
	  //testMirrorHorizontalBotToTop(); //done
    //testMirrorTemple(); //done
   // testMirrorArms(); //done
	 // testSharpen(0,0,500,400);
    //testMirrorGull();  //done
    //testMirrorDiagonal(); //done
    //testCollage();
    //testCopy();
   // testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}