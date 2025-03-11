class Ambiente {
    private int altura;
    private int largura;

    public Ambiente(int x, int y) {
        altura = x;
        largura = y;
    }

    public void dentroDosLimites(int x, int y) {
        if (x < 0 || x >= largura || y < 0 || y >= altura) {
            System.out.println("O robô ultrapassou os limites do ambiente");
        }
    }
}