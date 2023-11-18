package fr.ensimag.animaux;

public class Pangolin {
  // Ci-dessous la déclaration des attributs de la classe
  private double x;
  private double y;
  private String name;
  private int nbEcailles;

  // La déclaration des méthodes avec leur code
  public void tranlater(double dx, double dy) {
      x += dx;
      y += dy;
  }

  public void crier() {
      System.out.println("Gwwark Rhââgn Bwwikk"); // Cri du pangolin
  }
}