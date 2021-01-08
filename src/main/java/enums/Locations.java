package enums;

import com.example.demo.models.Location;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * enums
 *
 * @author wyvern800 - http://github.com/wyvern800
 * @created 07/01/2021 - 20:00
 * @project demo
 */
@Getter
public enum Locations {
    BECO(0, "map01", "Beco", 13, 42),
    ESTANDE_ARQUEIROS(1, "map02", "Estande dos Arqueiros", 26, 10),
    TEMPLO(2, "map03", "Templo", 27, 79),
    AVENIDA(3, "map04", "Avenida", 32, 57),
    ESCOLA(4, "map05", "Escola", 38, 33),
    LABORATORIO(5, "map06", "Laboratorio", 48, 39),
    HOTEL(6, "map07", "Hotel", 43, 13),
    LAGOA(7, "map08", "Lagoa", 46, 64),
    HOSTITAL(8, "map09", "Hospital", 55, 84),
    PRAIA(9, "map10", "Praia", 65, 9),
    FLORESTA(10, "map11", "Floresta", 63, 34),
    CEMITERIO(11, "map12", "Cemiterio", 66, 65),
    BAIRRO_NOBRE(12, "map13", "Bairro Nobre", 0, 0),
    CAPELA(13, "map14", "Capela", 0, 0),
    FABRICA(14, "map15", "Fabrica", 0, 0),
    DOCAS(15, "Map16", "Docas", 0, 0)
    ;

    private final int id;

    private final String pos;

    private final String name;

    private final int top;

    private final int left;

    private Location theLocation;

    Locations(int id, String pos, String name, int top, int left) {
        this.id = id;
        this.pos = pos;
        this.name = name;
        this.top = top;
        this.left = left;
    }
}
