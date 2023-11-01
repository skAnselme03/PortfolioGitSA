@php
    $currentRoute = \Request::route()->getName();
@endphp

@extends('layouts.default')

@section('content')
    <table class="table">
        <thead>
            <tr>
                <th>
                    <p><i>Auteur: {{ $link->person->UserName ?? 'Anonyme' }}</i></p>
                    <p><i>Date de publication: {{ $link->PublicationDate }}</i></p>
                </th>
                    <th style="text-align: center">Commentaires</th>
                    <th style="text-align: center">UpVotes</th>
                    <th style="text-align: center">DownVotes</th>
                </tr>
        </thead>
        <tbody class="table-group-divider">
            <tr>
                <td><a href="{{ $link->Link }}" target="_blank">{{ $link->Link }}</a></td>
                <td style="text-align: center">{{ $commentsCount }}</td>
                <td style="text-align: center">{{ $link->UpVote }}</td>
                <td style="text-align: center">{{ $link->DownVote }}</td>
            </tr>
        </tbody>
    </table>
    <p style="text-align: justify;">{{ $link->Description }}</p>
    <br />
    <div class="text-center">
        <form action="{{ route('link.upvote', ['id' => $link->ID]) }}" method="POST" class="d-inline">
            @csrf
            <button type="submit" class="btn btn-success">Upvote</button>
        </form>
        <form action="{{ route('link.downvote', ['id' => $link->ID]) }}" method="POST" class="d-inline">
            @csrf
            <button type="submit" class="btn btn-danger">Downvote</button>
        </form>
    </div>
    <br />
    <form action="{{ route('link.comment', ['id' => $link->ID]) }}" method="POST">
        @csrf
        <div class="mb-3">
            <label for="commentaire">Commentaire</label>
            <textarea name="commentaire" id="commentaire" class="form-control" rows="4" required></textarea>
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-primary">Soumettre le commentaire</button>
        </div>
    </form>
    <br />

    <table class="table">
        <tbody>
            @foreach ($comments as $comment)
            <tr>
                <td>
                    <strong>{{ $comment->person->UserName ?? 'Anonyme' }} a publié le {{ $comment->PublicationDate }}</strong>
                    <p>{{ $comment->Description }}</p>
                </td>
            </tr>
            @endforeach
        </tbody>
    </table>
@endsection
