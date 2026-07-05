from pathlib import Path


def strip_comments(code: str) -> str:
    result = []
    i = 0
    n = len(code)
    state = "code"
    quote = None

    while i < n:
        ch = code[i]
        if state == "code":
            if ch == '"' or ch == "'":
                quote = ch
                result.append(ch)
                i += 1
                state = "string"
            elif ch == '/' and i + 1 < n and code[i + 1] == '/':
                i += 2
                while i < n and code[i] != '\n':
                    i += 1
            elif ch == '/' and i + 1 < n and code[i + 1] == '*':
                i += 2
                while i < n and not (code[i] == '*' and i + 1 < n and code[i + 1] == '/'):
                    if code[i] == '\n':
                        result.append('\n')
                    i += 1
                i += 2 if i < n else 0
            else:
                result.append(ch)
                i += 1
        elif state == "string":
            if ch == '\\' and i + 1 < n:
                result.append(code[i:i+2])
                i += 2
            elif ch == quote:
                result.append(ch)
                i += 1
                state = "code"
            else:
                result.append(ch)
                i += 1
        else:
            result.append(ch)
            i += 1

    return ''.join(result)


def main():
    root = Path('src/main/java')
    files = sorted(root.rglob('*.java'))
    print(f'Processing {len(files)} Java files...')
    changed = 0
    for path in files:
        text = path.read_text(encoding='utf-8')
        new_text = strip_comments(text)
        if new_text != text:
            path.write_text(new_text, encoding='utf-8')
            print('Updated', path)
            changed += 1
    print(f'Done. Updated {changed} files.')


if __name__ == '__main__':
    main()
