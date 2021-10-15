package view;

import view.menu.Menu;

public interface IView {

    public void addMenu(Menu menu);
    public void removeMenu(Menu menu);
    public void clearAllMenus();
}
