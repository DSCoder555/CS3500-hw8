package cs3500.threetrios.newcode;

import cs3500.threetrios.originalcode.model.EmptyCard;
import cs3500.threetrios.originalcode.model.HoleCard;
import cs3500.threetrios.providercode.model.Card;
import cs3500.threetrios.providercode.model.CellType;
import cs3500.threetrios.providercode.model.GridCell;

public class GridCellAdapter implements GridCell {
  private CellType type;
  private Card card;
  public GridCellAdapter(cs3500.threetrios.originalcode.model.Card cellData){
    if (cellData instanceof HoleCard){
      type = CellType.Hole;
      card = null;
    }
    else{
      type = CellType.Cardholder;
      if (cellData instanceof EmptyCard){
        card = null;
      }
      else{
        card = new CardAdapter(cellData);
      }
    }
  }
  /**
   * Retrieves the type of this cell.
   *
   * @return the CellType representing the type of this cell
   */
  @Override
  public CellType getCellType() {
    return type;
  }

  /**
   * Retrieves the card currently in this cell, if any.
   *
   * @return the Card present in this cell, or null if the cell is empty
   */
  @Override
  public Card getCard() {
    return card;
  }
}
