#!/usr/bin/env bash
imageId=$(cat image.txt)

payload='{"updates":[{"type":"web","docker_image":"'${imageId}'"}]}'

curl -n -X PATCH https://api.heroku.com/apps/korkischedulgeapk/formation \
-d "$payload" \
-H "Content-Type: application/json" \
-H "Accept: application/vnd.heroku+json; version=3.docker-releases" \
-H "Authorization: Bearer $HEROKU_API_KEY"
