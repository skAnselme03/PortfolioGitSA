<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>S'inscrire</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8f9fa;
        }

        .container {
            max-width: 400px;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
        }

        label {
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 4px;
            border: 1px solid #ced4da;
        }

        button[type="submit"] {
            display: block;
            width: 100%;
            padding: 10px;
            border-radius: 4px;
            border: none;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        p {
            text-align: center;
        }

        a {
            color: #007bff;
        }
    </style>
</head>

<body>
    <div class="container">
        <h1>S'inscrire</h1>

        <form method="POST" action="{{ route('register') }}">
            @csrf

            <div>
                <label for="username">Pseudo</label>
                <input type="text" id="username" name="username" value="{{ old('username') }}" required autofocus>
                @error('username')
                    <span>{{ __('Pseudo invalide') }}</span>
                @enderror
            </div>

            <div>
                <label for="email">Courriel</label>
                <input type="email" id="email" name="email" value="{{ old('email') }}" required>
                @error('email')
                    <span>{{ __('Courriel invalide') }}</span>
                @enderror
            </div>

            <div>
                <label for="password">Mot de passe</label>
                <input type="password" id="password" name="password" required>
                @error('password')
                    <span>{{ __('Mot de passe invalide') }}</span>
                @enderror
            </div>

            <div>
                <button type="submit">S'enregistrer</button>
            </div>
        </form>

        <p>Vous avez déjà un compte ? <a href="{{ route('login') }}">Connectez-vous!</a></p>
    </div>
</body>

</html>
