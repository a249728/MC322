package ambiente;

public interface Entidade {
    int getX(); // Retorna a coordenada X da entidade
    int getY(); // Retorna a coordenada Y da entidade
    int getZ(); // Retorna a coordenada Z da entidade
    TipoEntidade getTipo(); // Retorna o tipo da entidade
    String getDescricao(); // Retorna uma descrição textual da entidade
    char getRepresentacao(); // Retorna o caractere que representa a entidade visualmente
}
