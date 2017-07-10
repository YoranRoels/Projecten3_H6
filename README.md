# Projecten3_H6

Show branches: git branch

Pull : git pull ('git up' to pull all branches)

Branch workflow:

  **Create branch (without <>)**
  
    git checkout -b <branchname>
 
  **Check which files got changed if you only want to push specific files**

    git status
 
  **Add the files you want to push**
  
    git add *
    
  **Commit to repository**
  
    git commit -m "message"
    
  Commit message example: "Change directory names." - "Add button to activate a bonus." - "Remove ..." - "Fix ..."
  A short explanation of 'how' is also advised when fixing a bug.
  DO NOT USE: 'changed', 'added', 'removed', 'fixed', etc...
  
  **Push**
  
    git push


# AngularApp
## Setup
Npm install
## Structure
| Bin          	| generally not needed                                                                                 	|
|--------------	|------------------------------------------------------------------------------------------------------	|
| Config       	| Password Strategy                                                                                    	|
| Models       	| Contains schema's for the objects used(mongoose)                                                     	|
| Node_modules 	| External modules, not needed(npm install should fix these)                                           	|
| Public       	| This is where we're going to store JavaScript, CSS, images, and templates we want the client to use. 	|
| Routes       	| Contains node controllers, contains all backend code                                                 	|
| Views        	| Views                                                                                                	|
