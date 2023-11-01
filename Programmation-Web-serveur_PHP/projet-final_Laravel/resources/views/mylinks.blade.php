@php
    use Illuminate\Support\Str;
    $currentRoute = \Request::route()->getName();
@endphp

@extends('layouts.default')

@section('content')
    @if(isset($_SESSION['username']))
        <h2>Salut, {{ $_SESSION['username'] }}!</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Liens</th>
                    <th style="text-align: center">Commentaires</th>
                    <th style="text-align: center">UpVotes</th>
                    <th style="text-align: center">DownVotes</th>
                </tr>
            </thead>
            <tbody class="table-group-divider">
                @foreach($myLinks as $link)
                    <tr>
                        <td>
                            <p><a href="{{ route('link.show', ['id' => $link->ID]) }}">{{ Str::limit($link->Description, 60, '...') }}</a></p>
                            <p><i>Auteur: {{ $link->person->UserName ?? 'Anonyme' }}</i></p>
                            <p><i>Date de publication: {{ $link->PublicationDate }}</i></p>
                        </td>
                        <td style="text-align: center">{{ $commentsCounts[$link->ID] ?? 0 }}</td>
                        <td style="text-align: center">{{ $link->UpVote }}</td>
                        <td style="text-align: center">{{ $link->DownVote }}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
    @else
        <p>Veuillez <a href="{{ route('login') }}">vous connecter</a> pour afficher tous les liens.</p>
        <!-- Il ne devrait pas être possible de voir ce message. -->
    @endif
@endsection
