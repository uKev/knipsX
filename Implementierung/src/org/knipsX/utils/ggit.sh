echo "<SKRIPT> start!"
git pull origin master
echo "<SKRIPT> add..."
git add .
echo "<SKRIPT> commit..."
git commit -am "XML update"
echo "<SKRIPT> push..."
git push origin master
echo "<SKRIPT> pull..."
git pull origin master
echo "<SKRIPT> ready..."

