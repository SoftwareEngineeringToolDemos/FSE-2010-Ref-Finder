/* 
*    Ref-Finder
*    Copyright (C) <2015>  <PLSE_UCLA>
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package tyRuBa.modes;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import tyRuBa.engine.RBVariable;

public class BindingEnv
  extends Hashtable
  implements Cloneable
{
  public BindingMode getBindingMode(RBVariable var)
  {
    return (BindingMode)get(var);
  }
  
  public BindingMode putBindingMode(RBVariable var, BindingMode bindingmode)
  {
    return (BindingMode)put(var, bindingmode);
  }
  
  public Object clone()
  {
    BindingEnv cl = (BindingEnv)super.clone();
    return cl;
  }
  
  public BindingEnv intersection(BindingEnv other)
  {
    BindingEnv result = (BindingEnv)clone();
    for (Iterator iter = result.keySet().iterator(); iter.hasNext();)
    {
      RBVariable var = (RBVariable)iter.next();
      if (!other.isBound(var)) {
        iter.remove();
      }
    }
    return result;
  }
  
  public boolean isBound(RBVariable var)
  {
    BindingMode result = getBindingMode(var);
    return result != null;
  }
}
