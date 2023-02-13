package portal.repository;

import java.util.List;

import portal.business.Menuitem;

public interface MenuitemRepository {
    List<Menuitem> findByMenuid(int menuid);
    List<Menuitem> findByParentid(int parentid);
}
