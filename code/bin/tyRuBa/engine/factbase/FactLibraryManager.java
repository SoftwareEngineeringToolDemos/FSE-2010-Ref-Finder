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
package tyRuBa.engine.factbase;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import tyRuBa.engine.PredicateIdentifier;
import tyRuBa.engine.QueryEngine;
import tyRuBa.engine.compilation.CompilationContext;
import tyRuBa.engine.compilation.Compiled;
import tyRuBa.engine.factbase.hashtable.Index;
import tyRuBa.engine.factbase.hashtable.URLFactLibrary;
import tyRuBa.modes.BindingList;
import tyRuBa.modes.Mode;
import tyRuBa.modes.Multiplicity;
import tyRuBa.modes.PredicateMode;

public class FactLibraryManager
{
  private HashMap libraries;
  private QueryEngine qe;
  
  public FactLibraryManager(QueryEngine qe)
  {
    this.qe = qe;
    removeAll();
  }
  
  public void addLibraryJarFile(String jarFileLocation)
  {
    if (!this.libraries.containsKey(jarFileLocation))
    {
      File jarFile = new File(jarFileLocation);
      try
      {
        this.libraries.put(jarFileLocation, new URLFactLibrary("jar:" + jarFile.toURL() + "!/", this.qe));
      }
      catch (MalformedURLException e)
      {
        throw new Error("Jar file location is not a valid location (it can't be turned into a URL)");
      }
    }
  }
  
  public void addLibraryURLLocation(String baseURL)
  {
    if (!this.libraries.containsKey(baseURL)) {
      this.libraries.put(baseURL, new URLFactLibrary(baseURL, this.qe));
    }
  }
  
  public URLFactLibrary getLibrary(String location)
  {
    return (URLFactLibrary)this.libraries.get(location);
  }
  
  public void removeLibrary(String location)
  {
    this.libraries.remove(location);
  }
  
  public void removeAll()
  {
    this.libraries = new HashMap();
  }
  
  public Compiled compile(PredicateMode pm, PredicateIdentifier predId, CompilationContext context)
  {
    if (this.libraries.size() == 0) {
      return Compiled.fail;
    }
    Index[] indexes = new Index[this.libraries.size()];
    int i = 0;
    for (Iterator iter = this.libraries.values().iterator(); iter.hasNext();)
    {
      URLFactLibrary element = (URLFactLibrary)iter.next();
      indexes[i] = element.getIndex(predId.getName(), predId.getArity(), pm);
      i++;
    }
    if (pm.getMode().hi.compareTo(Multiplicity.one) <= 0)
    {
      if (pm.getParamModes().getNumFree() != 0) {
        return new FactLibraryManager.1(this, pm.getMode(), indexes);
      }
      return new FactLibraryManager.2(this, pm.getMode(), indexes);
    }
    if (pm.getParamModes().getNumFree() != 0) {
      return new FactLibraryManager.3(this, pm.getMode(), indexes);
    }
    throw new Error("This case should not happen");
  }
}
