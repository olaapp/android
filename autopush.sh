today=`date '+%d-%m-%Y'`;
read -p "Commit description: " desc
git add . && \
git add -u && \
git commit -a -m "Update $today $desc" && \
git push 