import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticleApiService } from '../../services/article-api.service';
import { Article } from '../../interfaces/article.interface';
import { CommentsServiceService } from '../../services/comments-service.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommentResponse } from '../../interfaces/api/commentResponse.interface';
import { CommentRequest } from '../../interfaces/api/commentRequest.interface';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { CommentsResponse } from '../../interfaces/api/commentsResponse.interface';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { User } from 'src/app/interfaces/user.interface';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit, OnDestroy {
  public article: Article | undefined;
  public articleId = this.route.snapshot.paramMap.get('id')!;
  private httpSubscriptions: Subscription[] = [];
  public user!: User;

  public userId: string;
  public commentForm!: FormGroup;
  public  comments$: Observable<CommentsResponse> = this.commentService.allcomments(this.articleId);
  
  constructor( private authService: AuthService, private route: ActivatedRoute, private articleApiService: ArticleApiService, private commentService: CommentsServiceService,
    private matSnackBar: MatSnackBar, private fb: FormBuilder, private router: Router) {
  this.userId = localStorage.getItem('userID')!,
  this.initCommentForm()}

  ngOnInit(): void {
    this.httpSubscriptions.push(this.authService.getUserById(this.userId).subscribe((user: User) => (this.user = user)));

    this.httpSubscriptions.push(this.articleApiService.detail(this.articleId).subscribe((article: Article)=>this.article = article));
  }
  public back() {
    window.history.back();
  }
  public addComment(): void {
    const comment = {
      userId: +this.userId,
      articleId: this.article?.id,
      content: this.commentForm.value.comment
    } as CommentRequest;
    this.httpSubscriptions.push(this.commentService.send(comment).subscribe((commentResponse: CommentResponse)=>
    {this.matSnackBar.open(commentResponse.message, "Close", { duration: 3000});
    window.location.reload();

  }))
  }
  private initCommentForm() {
    this.commentForm = this.fb.group({
      comment: ['', [Validators.required, Validators.min(10)]],
    });
  }
  ngOnDestroy(): void {
    this.httpSubscriptions.forEach(subscribtion=> subscribtion.unsubscribe());
  }
}
