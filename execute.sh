echo $INTERNAL_BASE_URL
curl --location -v --request POST "$INTERNAL_BASE_URL/api/cinema-app/process/execute/all"

curl --location -v --request POST "$INTERNAL_BASE_URL/api/cinema-app/process/execute/item/all"