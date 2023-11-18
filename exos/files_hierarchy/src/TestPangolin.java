
import java.util.HashSet;
import java.util.Set;

import fr.ensimag.animaux.Pangolin;

// Le nom de la classe est à votre convenance.
// L'usage est d'appeler TestXXX une classe qui contient le programme principal
public class TestPangolin {
  public static void main(String[] args) {
    // Le tableau args, de taille  args.length, contient les arguments du programme
    // Ici définissez les instructions de votre programme principal
    Pangolin p = new Pangolin();
    p.crier();
    System.out.println("lala");

    Set<Integer> s = new HashSet<Integer>();
  }
}