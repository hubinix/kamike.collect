/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity.db;

import cn.exinhua.fetch.entity.Link;
import com.kamike.db.DbInst;
import com.kamike.db.generic.BaseReader;
import com.kamike.db.generic.GenericSelect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THiNk
 */
public class LinkReader extends BaseReader<Link> {

    @Override
    public GenericSelect<Link> createSelect() {
        return new LinkSelect();
    }

    @Override
    public GenericSelect<Link> createSelect(Link t) {
        return new LinkSelect(t);
    }

    @Override
    public ArrayList<Link> find(Link t) {
        GenericSelect<Link> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        ArrayList<Link> ret = null;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.rawSql() + "where t.url=? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, t.getUrl());

            rs = ps.executeQuery();
            ret = select.fetch(rs);

        } catch (Exception e) {
            ret = new ArrayList<>();
            System.out.println(this.getClass().getName() + e.toString());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LinkReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }

    public ArrayList<Link> findByRoot(String root, long offset, long size) {
        GenericSelect<Link> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        ArrayList<Link> ret = null;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.rawSql() + "where t.root=? and expired=0 and fetched=0 order by create_date desc limit ?,?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, root);
            ps.setLong(2, offset);
            ps.setLong(3, size);
            rs = ps.executeQuery();
            ret = select.fetch(rs);

        } catch (Exception e) {
            ret = new ArrayList<>();
            System.out.println(this.getClass().getName() + e.toString());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LinkReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }

    public Link getByUrl(String url) {
        GenericSelect<Link> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        Link ret = null;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.rawSql() + "where  t.url=? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, url);

            rs = ps.executeQuery();
            ret = select.fetchOnce(rs);

        } catch (Exception e) {
            ret = null;
            System.out.println(this.getClass().getName() + e.toString());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LinkReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }

    public long count(String url) {
        GenericSelect<Link> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        long ret = 0L;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.countSQL(select.rawSql() + "where  t.url=? "), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, url);

            rs = ps.executeQuery();
            ret = select.count(rs);

        } catch (Exception e) {
            ret = 0L;
            System.out.println(this.getClass().getName() + e.toString());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LinkReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }

    public long countByRootAndDepth(String root, int depth) {
        GenericSelect<Link> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        long ret = 0L;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.countSQL(select.rawSql() + "where  t.root=? and t.depth=? "), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, root);
            ps.setInt(2, depth);
            rs = ps.executeQuery();
            ret = select.count(rs);

        } catch (Exception e) {
            ret = 0L;
            System.out.println(this.getClass().getName() + e.toString());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LinkReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }
    
     public long countFetchedByRootAndDepth(String root, int depth) {
        GenericSelect<Link> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        long ret = 0L;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.countSQL(select.rawSql() + "where  t.root=? and t.depth=? and (t.fetched=1 or t.expired=1) "), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, root);
            ps.setInt(2, depth);
            rs = ps.executeQuery();
            ret = select.count(rs);

        } catch (Exception e) {
            ret = 0L;
            System.out.println(this.getClass().getName() + e.toString());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LinkReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }
     
     public long countHash(String hash) {
        GenericSelect<Link> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        long ret = 0L;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.countSQL(select.rawSql() + "where  t.hash=?  "), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, hash);
           
            rs = ps.executeQuery();
            ret = select.count(rs);

        } catch (Exception e) {
            ret = 0L;
            System.out.println(this.getClass().getName() + e.toString());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LinkReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }
     
      public long countUnfetchedByRoot(String root) {
        GenericSelect<Link> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        long ret = 0L;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.countSQL(select.rawSql() + "where  t.root=? and (t.fetched=0 or t.expired=0) "), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, root);
            
            rs = ps.executeQuery();
            ret = select.count(rs);

        } catch (Exception e) {
            ret = 0L;
            System.out.println(this.getClass().getName() + e.toString());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LinkReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }


    public Link getById(String id) {
        GenericSelect<Link> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        Link ret = null;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.rawSql() + "where  t.id=? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, id);

            rs = ps.executeQuery();
            ret = select.fetchOnce(rs);

        } catch (Exception e) {
            ret = null;
            System.out.println(this.getClass().getName() + e.toString());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LinkReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }

}
