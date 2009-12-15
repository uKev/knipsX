package org.knipsX.view.diagrams;

public enum CyclePerspectives {
	
	XYPLANE {
		@Override
		public void enforce(JAbstract3DView view) {
			// TODO Auto-generated method stub
			
		}
	},
	XZPLANE {
		@Override
		public void enforce(JAbstract3DView view) {
			// TODO Auto-generated method stub
			
		}
	},
	YZPLANE {
		@Override
		public void enforce(JAbstract3DView view) {
			// TODO Auto-generated method stub
			
		}
	},
	PERSPECTIVE {
		@Override
		public void enforce(JAbstract3DView view) {
			// TODO Auto-generated method stub
			
		}
	};
	
	/**
	 * Enforces the selected view configuration upon the JAbstract3DView specified
	 * @param view the view you want to change
	 */
	public abstract void enforce(JAbstract3DView view);
	
	
	
	/**
	 * Saves a Reference to the currently selected CycleViews enum
	 */

	static CyclePerspectives currentView; 
}


