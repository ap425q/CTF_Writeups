from collections import defaultdict

def getUnexpiredTokens(time_to_live, queries):
    token_expiry = defaultdict(int)
    count_unexpired_tokens = []

    for query in queries:
        query_parts = query.split()
        query_type, *query_args = query_parts

        if query_type == "generate" and len(query_args) == 2:
            token_id, current_time = query_args
            current_time = int(current_time)

            token_expiry[token_id] = current_time + time_to_live

        elif query_type == "renew" and len(query_args) == 2:
            token_id, current_time = query_args
            current_time = int(current_time)

            if token_id in token_expiry and token_expiry[token_id] > current_time:
                token_expiry[token_id] = current_time + time_to_live

        elif query_type == "count" and len(query_args) == 1:
            current_time = int(query_args[0])
            count = 0
            for token, expiry_time in token_expiry.items():
                if expiry_time > current_time:
                    count += 1
            count_unexpired_tokens.append(count)

    return count_unexpired_tokens

# Example usage
time_to_live = 35
queries = [
    "generate token 3",
    "count 4",
    "generate token2 6",
    "count 7",
    "generate token3 11",
    "count 41"
]

result = getUnexpiredTokens(time_to_live, queries)
print(result)  # Output should be [1, 0]
